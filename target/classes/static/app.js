const api = '/api/expenses';
const form = document.querySelector('#expense-form');
const body = document.querySelector('#expenses-body');
const filter = document.querySelector('#category-filter');
const search = document.querySelector('#expense-search');
const message = document.querySelector('#form-message');
const money = new Intl.NumberFormat('en-IN', { style: 'currency', currency: 'INR' });

document.querySelector('#date').value = new Date().toISOString().slice(0, 10);
document.querySelector('#today').textContent = new Intl.DateTimeFormat('en-IN', { weekday: 'long', month: 'short', day: 'numeric' }).format(new Date());

function escapeHtml(value) {
  return String(value).replace(/[&<>'"]/g, character => ({ '&':'&amp;', '<':'&lt;', '>':'&gt;', "'":'&#039;', '"':'&quot;' })[character]);
}

function formatDate(value) {
  return new Intl.DateTimeFormat('en-IN', { day: 'numeric', month: 'short', year: 'numeric' }).format(new Date(`${value}T00:00:00`));
}

async function request(path = '') {
  const response = await fetch(`${api}${path}`);
  if (!response.ok) throw new Error('Unable to load expenses.');
  return response.json();
}

async function refresh() {
  try {
    const params = new URLSearchParams();
    if (filter.value) params.set('category', filter.value);
    if (search.value.trim()) params.set('search', search.value.trim());
    const [expenses, total, totals] = await Promise.all([
      request(params.toString() ? `?${params}` : ''),
      request('/total'), request('/totals-by-category')
    ]);
    render(expenses, total.total, totals);
  } catch (error) { message.textContent = error.message; }
}

function render(expenses, total, totals) {
  document.querySelector('#overall-total').textContent = money.format(total);
  document.querySelector('#expense-count').textContent = expenses.length;
  const categories = Object.keys(totals).sort((a, b) => a.localeCompare(b));
  document.querySelector('#category-count').textContent = categories.length;
  filter.innerHTML = '<option value="">All categories</option>' + categories.map(category => `<option ${filter.value === category ? 'selected' : ''}>${escapeHtml(category)}</option>`).join('');
  document.querySelector('#category-totals').innerHTML = categories.map(category => `<span class="chip">${escapeHtml(category)} · ${money.format(totals[category])}</span>`).join('');
  body.innerHTML = expenses.map(expense => `<tr><td>${escapeHtml(expense.title)}</td><td><span class="category-label">${escapeHtml(expense.category)}</span></td><td>${formatDate(expense.date)}</td><td>${money.format(expense.amount)}</td><td><button class="delete" title="Delete ${escapeHtml(expense.title)}" aria-label="Delete ${escapeHtml(expense.title)}" data-id="${expense.id}">×</button></td></tr>`).join('');
  document.querySelector('#empty-state').classList.toggle('visible', expenses.length === 0);
}

form.addEventListener('submit', async event => {
  event.preventDefault(); message.textContent = '';
  const button = document.querySelector('#submit-button'); button.disabled = true; button.textContent = 'Saving…';
  const payload = Object.fromEntries(new FormData(form)); payload.amount = Number(payload.amount);
  try {
    const response = await fetch(api, { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(payload) });
    if (!response.ok) { const error = await response.json(); throw new Error(error.message || 'Unable to add this expense.'); }
    form.reset(); document.querySelector('#date').value = new Date().toISOString().slice(0, 10); await refresh();
  } catch (error) { message.textContent = error.message; }
  finally { button.disabled = false; button.innerHTML = 'Add expense <span>→</span>'; }
});

filter.addEventListener('change', refresh);
let searchDelay;
search.addEventListener('input', () => { clearTimeout(searchDelay); searchDelay = setTimeout(refresh, 200); });
body.addEventListener('click', async event => {
  const button = event.target.closest('.delete'); if (!button) return;
  if (!confirm('Delete this expense?')) return;
  await fetch(`${api}/${button.dataset.id}`, { method: 'DELETE' }); await refresh();
});
refresh();
