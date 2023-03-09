function filter() {
  var value, nickname, item, i;

  value = document.getElementById("value").value.toUpperCase();
  item = document.getElementsByClassName("item");

  for (i = 0; i < item.length; i++) {
    nickname = item[i].getElementsByClassName("nickname");
    if (nickname[0].innerHTML.toUpperCase().indexOf(value) > -1) {
      item[i].style.display = "revert";
    } else {
      item[i].style.display = "none";
    }
  }
}
