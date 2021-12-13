const menuItems = Array.from(document.querySelectorAll('.sidebar__menu--item'));
menuItems.forEach(item => {
    item.addEventListener('click', e => {
        menuItems.forEach(item => {
            item.classList.remove('is-active');
        });
        e.currentTarget.classList.add('is-active');
    });
});

function userServlet(name){
    window.location.href=name;
}

