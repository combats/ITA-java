/**
 * Created by pjatochkin on 10-Aug-2014.
 */
$('h3 span').each(function(index, element) {
    var name = $(element).text().split(/\s+/)[1];
    if (name === 'Sasha') {
        $(element).click();
    };
});