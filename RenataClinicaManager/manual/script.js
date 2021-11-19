var selected;
function onChange(element, classe) {
    var elementClass = element.querySelector("." + classe).className;
    if (elementClass.includes("fa-angle-right")) {
        element.querySelector("." + classe).className = "fas fa-angle-down";
        element.nextElementSibling.style = `
            visibility: visible;
            transition: all 0.3s ease 0s;
            max-height: 500px;
        `;
    }
    else {
        element.querySelector("." + classe).className = "fas fa-angle-right";
        element.nextElementSibling.style = `
            visibility: hidden;
            transition-delay: 0s, 0s, 0.3s;
            max-height: 0;
        `;
    }
}