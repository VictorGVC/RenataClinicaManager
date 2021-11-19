function validaStyle() {
    if (selected) {
        selected.style = 'transition: .2s color;';
    }
}

function navegacao(object) {
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Introdução</label>
            <label class="sub-title">Navegação</label>
            <p>Navegue pelo índice à esquerda para pular aos tópicos desejados. Itens indicados com o com ícone "<i class="fas fa-angle-right"></i>" podem ser expandidos ao clicá-los.</p>
            <div id="imagens">
                <img src="./images/indice.png" alt="Indice"/>
                <img src="./images/indiceExpandido.png" alt="Indice Expandido"/>
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

/*
function camposObrigatorios(object) {
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Introdução</label>
            <label class="sub-title">Campos Obrigatórios</label>
            <p>Todos os campos que são obrigatórios preencher contém um ícone "*" ao lado do nome. Caso o campo não tenha sido informado ou tenha sido preenchido incorretamente, o sistema irá exibir uma mensagem de erro informando o primeiro campo incorreto.</p>
            <div id="imagens">
                <img src="./images/exemploObrigatorio.png" alt="Exemplo Obrigatório"/>
                <img src="./images/exemploObrigatorioErro.png" alt="Exemplo Obrigatorio com Erro"/>
                <img src="./images/exemploCpf.png" alt="Exemplo CPF"/>
                <img src="./images/exemploCpfInvalido.png" alt="Exemplo de CPF Inválido"/>
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function camposLista(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Introdução</label>
            <label class="sub-title">Campos do Tipo Lista</label>
            <p>Os campos do tipo lista são campos que carregam opções predefinidas, para acessa-las clique no ícone "<i class="fas fa-angle-down"></i>" do lado direito. Escolha uma das opções e clique para seleciona-la.</p>
            <div id="imagens">
                <img src="./images/exemploLista.png" alt="Exemplo de Lista"/>
                <img src="./images/exemploListaExpandido.png" alt="Exemplo de Lista Expandida"/>
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function camposData(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Introdução</label>
            <label class="sub-title">Campos de Data</label>
            <p>Os campos de data podem ser preenchidos digitando a data diretamente, ou clicando no ícone "<img src="./images/dataIcon.png" alt="Icone de Data"/>" do lado direito. Um calendário irá abrir para selecionar uma data.</p>
            <div id="imagens">
                <img src="./images/exemploData.png" alt="Exemplo de Data"/>
                <img src="./images/exemploDataExpandido.png" alt="Exemplo de Data Expandida"/>
                <img src="./images/exemploDataModificada.png" alt="Exemplo de Data Modificada"/>
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function camposEntrada(object) {
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Introdução</label>
            <label class="sub-title">Campos de Entrada</label>
            <p>Os campos de entrada que buscam dados de outras telas tem 4 formas de serem preenchidos. Por um campo selecionado, ou período, por um filtro, pelo filtro, filtro extra ou a partir do botão Buscar.</p>
            <div id="imagens">
                <img src="./images/exemploPesquisa.png" alt="Exemplo de Entrada"/>
            </div>
            <label class="sub-title">1.1 Pelo Campo Selecionado por um filtro</label>
            <p>Você escolhe um filtro no campo "Filtrar" e digita no campo "Buscar", conforme você vai digitando o sistema vai buscando os dados que coincidem com o texto digitado</p>
            <div id="imagens">
                <img src="./images/filtrar.png" alt="Exemplo de escolha de filtro"/>
                <img src="./images/buscar.png" alt="Exemplo de busca"/>
            </div>
            <label class="sub-title">1.2 Por um Período Selecionado por um filtro</label>
            <p>Você escolhe um filtro de data no campo "Filtrar" e informa a data inicial e final, assim o sistema irá buscar os dados que estão entre a data inicial e final.</p>
            <div id="imagens">
                <img src="./images/filtrarData.png" alt="Exemplo de escolha de filtro de data"/>
                <img src="./images/buscarData.png" alt="Exemplo de Datas"/>
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}
*/