window.initAutocomplete = function(tags) {
    $(function () {
        var tagsData = tags;
        var tagNames = tagsData.map(function (tag) {
            return {label: tag.name, value: tag.id};
        });

        var selectedTags = [];

        setupAutocomplete();

        function setupAutocomplete() {
            $("#tag-input").autocomplete({
                minLength: 1,
                source: function (request, response) {
                    var filtered = $.grep(tagNames, function (tag) {
                        return tag.label.toLowerCase().indexOf(request.term.toLowerCase()) !== -1;
                    });
                    response(filtered);
                },
                select: function (event, ui) {
                    if (!selectedTags.some(function (t) {
                        return t.value === ui.item.value;
                    })) {
                        selectedTags.push(ui.item);
                        updateSelectedTags();
                    }
                    $("#tag-input").val('');
                    return false;
                },
                focus: function (event, ui) {
                    return false;
                }
            });
        }

        function updateSelectedTags() {
            var container = $("#selected-tags");
            container.empty();
            var ids = [];
            selectedTags.forEach(function (tag) {
                var span = $('<span></span>')
                    .text(tag.label + ' ×')
                    .css({
                        padding: '2px 8px',
                        margin: '2px',
                        border: '1px solid #eee',
                        borderRadius: '6px',
                        cursor: 'pointer',
                        display: 'inline-block'
                    })
                    .click(function () {
                        selectedTags = selectedTags.filter(function (t) {
                            return t.value !== tag.value;
                        });
                        updateSelectedTags();
                    });
                container.append(span);
                ids.push(tag.value);
            });
            $('#selected-tag-ids').val(ids.join(','));
        }
    });
};
