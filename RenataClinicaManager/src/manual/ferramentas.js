function backup(object){
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

function restore(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Ferramentas</label>
            <label class="sub-title">Restore</label>
            <p>Para fazer restore é preciso clicar em ferramentas na tela principal e depois clicar em restaurar, então uma 
            janela de confirmação irá aparecer, após confirmar aparecerá uma janela confirmando a operação</p>
            <div id="imagens">
                <img src="./images/restore.png" alt="Restore"/>
                <img src="./images/restoreConfirmacao.png" alt="Confirmação de Restore"/>
            </div>
            </br>
            <p style="color: red">OBS: Tenha muito cuidado ao fazer restore, quando feito, todas as informações 
            serão perdidas e retornarão na ultima versão em que foi feito o backup.</p>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function feriados(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Ferramentas</label>
            <label class="sub-title">Feriados</label>
            <p>Para carregar os feriados do ano é necessário clicar em ferramentas na tela principal e depois clicar em carregar feriados,
            então uma janela de confirmação irá aparecer, após confirmar uma notificação em verde confirmará a operação</p>
            <div id="imagens">
                <img src="./images/feriados.png" alt="Feriados"/>
                <img src="./images/feriadosConfirmacao.png" alt="Confirmação de Feriados"/>
            </div>
            </br>
            <p style="color: red">OBS: Para carregar os feriados de uma vez da web é necessário estar conectado a internet.</p>
            <p style="color: red">OBS: Ao carregar feriados, os feriados antigos cadastrados serão apagados.</p>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}