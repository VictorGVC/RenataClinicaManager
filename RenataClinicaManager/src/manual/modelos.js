function modeloReceituario(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Modelos</label>
            <label class="sub-title">Modelo de Receituario</label>
            <p>Para cadastrar um modelo de receituário é necessário preencher os campos de cabeçalho e rodapé, uma notificação verde confirmará a operação.</p>
            
            <div id="imagens">
                <img src="./images/modeloReceituario.png" alt="Modelo de Receituário"/>
            </div>
            </br>
            <p style="color: red">OBS: É recomendado colocar as informações do dentista no cabeçalho do receituário incluindo o nome e o CRO, 
            e para o rodapé é recomendado inserir o endereço e as informações de contato</p>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function modeloAtestado(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Modelos</label>
            <label class="sub-title">Modelo de Atestado</label>
            <p>Para cadastrar um modelo de atestado é necessário preencher os campos de cabeçalho, lei e rodapé, uma notificação verde confirmará a operação.</p>
            
            <div id="imagens">
                <img src="./images/modeloAtestado.png" alt="Notificação de Sucesso"/>
            </div>
            </br>
            <p style="color: red">OBS: É recomendado colocar as informações do dentista no cabeçalho do receituário incluindo o nome e o CRO, 
            e para o rodapé é obrigatório inserir "Cidade/UF"</p>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}