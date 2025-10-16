document.addEventListener('DOMContentLoaded', function() {
    const tagLinks = document.querySelectorAll('.tag-name');
    const searchInput = document.querySelector('.search-input');

    tagLinks.forEach(function(link) {
        link.addEventListener('click', function(e) {
            e.preventDefault();

            const tag = this.getAttribute('data-tag');
            const currentValue = searchInput.value.trim();

            if (currentValue.length > 0) {
                searchInput.value = currentValue + ' ' + tag;
            } else {
                searchInput.value = tag;
            }
        });
    });
});