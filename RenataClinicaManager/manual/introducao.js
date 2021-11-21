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

function camposObrigatorios(object) {
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Introdução</label>
            <label class="sub-title">Campos Obrigatórios</label>
            <p>Todos os campos que são obrigatórios preencher contém um ícone "*" ao lado do nome. Caso o campo não tenha sido informado ou tenha sido preenchido incorretamente, o sistema irá exibir uma mensagem de erro informando o primeiro campo incorreto, colorindo na cor vermelha</p>
            <div id="imagens">
                <img src="./images/exemploObrigatorio.png" alt="Exemplo Obrigatório"/>
                <img src="./images/exemploCPF.png" alt="Exemplo CPF"/>
                <img src="./images/obrigatorioErro.png" alt="Exemplo Obrigatorio com Erro"/>
                <img src="./images/exemploCPFInvalido.png" alt="Exemplo de CPF Inválido"/>
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
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function mensagensErro(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Introdução</label>
            <label class="sub-title">Mensagens de Erro</label>
            <p>Existem dois tipos de mensagens de erro, quando falta alguma informação obrigatória ou quando acontece algum erro na transação</p>
            
            <div id="imagens">
                <img src="./images/obrigatorioErro.png" alt="Exemplo de Data"/>
                <img src="./images/exemploErro.png" alt="Exemplo de Data Expandida"/>
            </div>
            </br>
            <p style="color: red">OBS: geralmente ocorre erro em transações quando se está tentando excluir alguma coisa que dependa de outra, 
            quando ocorrer tente excluir primeiro os itens menores, por exemplo, não é possível excluir um atendimento que exista itens utilizados
            , primeiro é necessário excluir os itens, posteriormente excluir o atendimento</p>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function notificacoes(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Introdução</label>
            <label class="sub-title">Notificações</label>
            <p>Existem dois tipos de notificação, quando é confirmado uma ação com sucesso e quando tem alguma conta a pagar ou receber perto do 
            vencimento.</p>
            
            <div id="imagens">
                <img src="./images/notificacaoConfirmacao.png" alt="Notificação de Sucesso"/>
                <img src="./images/notificacaoContaR.png" alt="Notificação de Contas a Receber"/>
                <img src="./images/notificacaoContaP.png" alt="Notificação de Contas a Pagar"/>
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function cadastros(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Introdução</label>
            <label class="sub-title">Cadastros</label>
            <p>Todos os cadastros tem a mesma estrutura, então será utilizado um como exemplo, mas se aplica para todas as telas</p>
            </br>
            <label class="sub-title">Salvar</label>
            <p>Para salvar um novo registro primeiramente é necessário clicar no botão "novo" como mostrado abaixo</p>
            <div id="imagens">
                <img src="./images/basicaNovo.png" alt="Novo Registro"/>
            </div>
            <p>Será liberado os campos de digitação, então preencha com os dados necessários e clique em confirmar</p>
            <div id="imagens">
                <img src="./images/basicaCampos.png" alt="Campos Registro"/>
            </div>

            <label class="sub-title">Alterar</label>
            <p>Para alterar um registro primeiramente é necessário selecionar o registro, e clicar em "alterar" como mostrado abaixo</p>
            <div id="imagens">
                <img src="./images/basicaSelecionar.png" alt="Selecionar Registro"/>
            </div>
            <p>Será liberado os campos de digitação, então preencha com os dados necessários e clique em confirmar</p>
            <div id="imagens">
                <img src="./images/basicaCampos.png" alt="Campos Registro"/>
            </div>

            <label class="sub-title">Apagar</label>
            <p>Para apagar um registro primeiramente é necessário selecionar o registro, e clicar em "apagar" como mostrado abaixo</p>
            <div id="imagens">
                <img src="./images/basicaApagar.png" alt="Selecionar Registro"/>
            </div>
            <p>Será pedido para confirmar a exclusão, pois depois da exclusão não poderá ser desfeita, caso tenha certeza da exclusão, clique em "sim"</p>
            <div id="imagens">
                <img src="./images/basicaConfirmar.png" alt="Confirmar Exclusão"/>
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function filtros(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Introdução</label>
            <label class="sub-title">Filtros</label>
            <p>Todos os filtros tem o mesmo padrão, primeiramente é necessário escolher por qual campo será filtrada a tabela</p>
            
            <div id="imagens">
                <img src="./images/filtroEscolha.png" alt="Escolha do Tipo de Filtro"/>
            </div>

            <p>posteriormente é necessário digitar o que será filtrado</p>
            
            <div id="imagens">
                <img src="./images/filtroDigito.png" alt="Filtragem"/>
            </div>

            <p>Alguns filtros tem adições como datas, então sempre os filtros são entre a primeira e a segunda data por exemplo</p>
            
            <div id="imagens">
                <img src="./images/filtroData.png" alt="Filtro de Data"/>
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}