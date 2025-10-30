document.addEventListener('DOMContentLoaded', () => {
    const openModal = (el) => el?.classList.remove('oculto');
    const closeModal = (el) => el?.classList.add('oculto');
    document.querySelectorAll('[data-close]').forEach(btn => {
        btn.addEventListener('click', () => closeModal(btn.closest('.modal')));
    });
    document.querySelectorAll('.modal').forEach(m => {
        m.addEventListener('click', (e) => { if (e.target === m) closeModal(m); });
    });

    // Logout
    const btnLogoutOpen = document.getElementById('btn-logout-open');
    const modalLogout = document.getElementById('modal-logout');
    btnLogoutOpen?.addEventListener('click', () => openModal(modalLogout));

    // Añadir
    const btnAddOpen = document.getElementById('btn-add-open');
    const modalAdd = document.getElementById('modal-add');
    btnAddOpen?.addEventListener('click', () => {
        const form = document.getElementById('form-add');
        form.reset();
        openModal(modalAdd);
    });

    // Editar
    const modalEdit = document.getElementById('modal-edit');
    document.querySelectorAll('.btn-edit').forEach(btn => {
        btn.addEventListener('click', () => {
            const card = btn.closest('.event-card');
            if (!card) return;
            document.getElementById('edit-id').value = card.dataset.id;
            document.getElementById('edit-title').value = card.dataset.title || '';
            document.getElementById('edit-description').value = card.dataset.description || '';
            document.getElementById('edit-type').value = card.dataset.type || 'otro';
            document.getElementById('edit-date').value = card.dataset.date || '';
            openModal(modalEdit);
        });
    });

    // Eliminar
    const modalDelete = document.getElementById('modal-delete');
    document.querySelectorAll('.btn-delete').forEach(btn => {
        btn.addEventListener('click', () => {
            const card = btn.closest('.event-card');
            if (!card) return;
            document.getElementById('delete-id').value = card.dataset.id;
            openModal(modalDelete);
        });
    });

    // Fechas (Complicado)
    const relativeLabel = (isoDate) => {
        const today = new Date();
        const d = new Date(isoDate + 'T00:00:00');
        const msPerDay = 24 * 60 * 60 * 1000;
        const a = Date.UTC(today.getFullYear(), today.getMonth(), today.getDate());
        const b = Date.UTC(d.getFullYear(), d.getMonth(), d.getDate());
        const diff = Math.round((b - a) / msPerDay);

        if (diff === 0) return 'Hoy';
        if (diff > 0) return `En ${diff} día${diff === 1 ? '' : 's'}`;
        const nd = Math.abs(diff);
        return `Hace ${nd} día${nd === 1 ? '' : 's'}`;
    };

    document.querySelectorAll('.when-label').forEach(span => {
        const iso = span.dataset.date;
        if (iso) span.textContent = relativeLabel(iso);
    });

    // Validaciones
    const formAdd = document.getElementById('form-add');
    const formEdit = document.getElementById('form-edit');
    const must = (s) => s && s.trim().length > 0;

    formAdd?.addEventListener('submit', (e) => {
        const title = formAdd.querySelector('[name="title"]').value;
        const date  = formAdd.querySelector('[name="eventDate"]').value;
        if (!must(title) || !must(date)) { e.preventDefault(); alert('Completa título y fecha.'); }
    });

    formEdit?.addEventListener('submit', (e) => {
        const title = formEdit.querySelector('[name="title"]').value;
        const date  = formEdit.querySelector('[name="eventDate"]').value;
        if (!must(title) || !must(date)) { e.preventDefault(); alert('Completa título y fecha.'); }
    });
});