timeline = document.querySelector('.timeline');

fetch('script/data.json').then(resp => resp.json()).then(json => {
    for (var i = 0; i < json.length; i++) {
        var div = document.createElement('div');
        var img = document.createElement('img');
        var h3 = document.createElement('h3');
        h3.innerText = json[i].title;
        img.src = json[i].path;
        div.appendChild(img);
        div.appendChild(h3);
        div.classList.add('thumb');
        timeline.appendChild(div);
    }
});