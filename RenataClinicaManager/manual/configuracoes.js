function tema(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Configurações</label>
            <label class="sub-title">Tema</label>
            <p>Para alterar o tema de cores do sistema é necessário abrir a tela de configurações e clicar no tema que deseja, será feito uma animação confirmando a alteração.</p>
            <div id="imagens">
                <img src="./images/temaEscuro.png" alt="Tema Escuro"/>
                <img src="./images/temaClaro.png" alt="Tema Claro"/>
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function informacoes(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Configurações</label>
            <label class="sub-title">Informações da Empresa</label>
            <p>Para alterar as informações da empresa na tela inicial é necessário abrir a tela de configurações alterar os dados digitando e clicar em salvar</p>
            <div id="imagens">
                <img src="./images/informacoes.png" alt="Informações"/>
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}