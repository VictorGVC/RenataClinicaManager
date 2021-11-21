function receituario(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Impressão</label>
            <label class="sub-title">Impressão de Receituario</label>
            <p>Para imprimir um receituário é necessário preencher o campo de medicações com as medicações e o modo de usar e então clicando 
            no botão de confirmação, posteriormente aparecerá uma tela com o documento pronto, então é preciso clicar no botão de impressão para 
            abrir o menu de impressão e imprimir</p>
            
            <div id="imagens">
                <img src="./images/telaReceituario.png" alt="Tela de Receituário"/>
                <img src="./images/documentoReceituario.png" alt="documento do Receituário"/>
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function atestado(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Modelos</label>
            <label class="sub-title">Impressão de Atestado</label>
            <p>Para imprimir um Atestado é necessário primeiramento escolher o fim a que o receituário foi pedido (caso a opção outros
                 seja selecionada um campo de digitação será habilitado para digitar o motivo)</p>
            
            <div id="imagens">
                <img src="./images/atestadofim.png" alt="Fim do atestado"/>
            </div>

            <p>É preciso escolher o paciente que vai ser beneficiado pelo atestado.</p>
           
            <div id="imagens">
                <img src="./images/atestadoPaciente.png" alt="Paciente do atestado"/>
            </div>

            <p>Depois é necessário digitar algumas informações, sendo elas, o representante legal SE NECESSÁRIO, RG do paciente, o período em 
            horas em que o paciente estava em cuidados do dentista e o CID do documento</p>
           
            <div id="imagens">
                <img src="./images/atestadoInformacoes.png" alt="Informações do atestado"/>
            </div>

            <p>Depois é necessário escolher o tipo de retorno, caso seja escolhido "permanecer em repouso por:" ou "outros"
            um campo de digitação será habilitado para digitar a informação</p>
           
            <div id="imagens">
                <img src="./images/atestadoRetorno.png" alt="Retorno"/>
            </div>

            <p>E por fim é necessário escolher a data em que o paciente estava sob cuidados odontológicos e clicar no botão de confirmação</p>
           
            <div id="imagens">
                <img src="./images/atestadoData.png" alt="Data do procedimento"/>
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}