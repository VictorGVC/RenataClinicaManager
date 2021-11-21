function tratamento(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Funcionalidades</label>
            <label class="sub-title">Controlar Tratamento</label>
            <p>O funcionamento dessa função é parecida com um cadastro, então para criar um novo, alterar ou excluir um registro leia o tópico "Cadastro"
            mas para ativar e desativar um tratamento é necessário selecionar o registro de tratamento e clicar em ativar ou desativar.</p>
            <div id="imagens">
                <img src="./images/tratamentoIniciar.png" alt="Iniciar/Finalizar"/>
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function agendamento(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Funcionalidades</label>
            <label class="sub-title">Agendamento</label>
            <p>Para fazer o agendamento é necessário primeiramente selecionar um paciente como mostrado abaixo</p>
            <div id="imagens">
                <img src="./images/agendarPaciente.png" alt="Agendar Paciente"/>
            </div>

            <p>Selecione um tratamento (é possivel digitar para filtrar tratamentos)</p>
            <div id="imagens">
                <img src="./images/agendarTratamento.png" alt="Agendar Tratamento"/>
            </div>

            <p>Selecione a data</p>
            <div id="imagens">
                <img src="./images/agendarData.png" alt="Agendar Data"/>
            </div>

            <p>Selecione o horário e clique em Agendar</p>
            <div id="imagens">
                <img src="./images/agendarHorario.png" alt="Agendar Data"/>
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function historico(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Funcionalidades</label>
            <label class="sub-title">Histórico de Atendimentos</label>
            <p>Para visualizar o histórico de atendimentos é necessário apenas selecionar o paciente, então será exibido todos os atendimentos em
            ordem de data</p>
            <div id="imagens">
                <img src="./images/historico.png" alt="Backup"/>
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function recebimento(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Ferramentas</label>
            <label class="sub-title">Backup</label>
            <p>Para fazer backup é preciso clicar em ferramentas ta tela principal e depois clicar em salvar, então uma 
            janela de confirmação irá aparecer, após confirmar aparecerá uma janela confirmando a operação</p>
            <div id="imagens">
                <img src="./images/backup.png" alt="Backup"/>
                <img src="./images/backupConfirmacao.png" alt="Confirmação de Backup"/>
            </div>
            </br>
            <p style="color: red">OBS: sempre que fizer backup, o backup anterior será apagado, 
            então tenha certeza de que está salvando a versão correta dos dados.</p>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function compra(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Ferramentas</label>
            <label class="sub-title">Backup</label>
            <p>Para fazer backup é preciso clicar em ferramentas ta tela principal e depois clicar em salvar, então uma 
            janela de confirmação irá aparecer, após confirmar aparecerá uma janela confirmando a operação</p>
            <div id="imagens">
                <img src="./images/backup.png" alt="Backup"/>
                <img src="./images/backupConfirmacao.png" alt="Confirmação de Backup"/>
            </div>
            </br>
            <p style="color: red">OBS: sempre que fizer backup, o backup anterior será apagado, 
            então tenha certeza de que está salvando a versão correta dos dados.</p>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function pagamento(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Ferramentas</label>
            <label class="sub-title">Backup</label>
            <p>Para fazer backup é preciso clicar em ferramentas ta tela principal e depois clicar em salvar, então uma 
            janela de confirmação irá aparecer, após confirmar aparecerá uma janela confirmando a operação</p>
            <div id="imagens">
                <img src="./images/backup.png" alt="Backup"/>
                <img src="./images/backupConfirmacao.png" alt="Confirmação de Backup"/>
            </div>
            </br>
            <p style="color: red">OBS: sempre que fizer backup, o backup anterior será apagado, 
            então tenha certeza de que está salvando a versão correta dos dados.</p>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function atendimento(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Ferramentas</label>
            <label class="sub-title">Backup</label>
            <p>Para fazer backup é preciso clicar em ferramentas ta tela principal e depois clicar em salvar, então uma 
            janela de confirmação irá aparecer, após confirmar aparecerá uma janela confirmando a operação</p>
            <div id="imagens">
                <img src="./images/backup.png" alt="Backup"/>
                <img src="./images/backupConfirmacao.png" alt="Confirmação de Backup"/>
            </div>
            </br>
            <p style="color: red">OBS: sempre que fizer backup, o backup anterior será apagado, 
            então tenha certeza de que está salvando a versão correta dos dados.</p>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}