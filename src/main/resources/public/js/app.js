function FilterByCategory() {
    document.getElementById("myDropdown1").classList.toggle("show");
};
function FilterBySupplier() {
    document.getElementById("myDropdown2").classList.toggle("show");
};
window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {

        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
};