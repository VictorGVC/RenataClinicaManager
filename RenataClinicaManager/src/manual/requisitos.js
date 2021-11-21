function postgreSQL(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Requisitos</label>
            <label class="sub-title">Banco de Dados</label>
            <p>Para conseguir executar o programa é necessário que o banco de dados PostgreSQL esteja instalado na máquina, 
            para baixar acesse esse <a href="https://www.enterprisedb.com/downloads/postgres-postgresql-downloads">link</a>, 
            e clique para baixar como indicado a baixo</p>
            
            <div id="imagens">
                <img src="./images/postgreDownload.png" alt="Informações"/>
                </br>
            </div>

            <p>Execute o programa baixado e siga as instruções como indicado abaixo</p>

            <div id="imagens">
                <img src="./images/postgre1.png" alt="postgreSQL"/>
                </br>
                <img src="./images/postgre2.png" alt="postgreSQL"/>
                </br>
                <img src="./images/postgre3.png" alt="postgreSQL"/>
                </br>
            </div>

            <p>Digite postgres123 nas duas caixas de edição</p>

            <div id="imagens">
                <img src="./images/postgre4.png" alt="postgreSQL"/>
                </br>
                <img src="./images/postgre5.png" alt="postgreSQL"/>
                </br>
                <img src="./images/postgre6.png" alt="postgreSQL"/>
                </br>
                <img src="./images/postgre7.png" alt="postgreSQL"/>
                </br>
            </div>

            <p>desmarque a caixa indicada e clique em next para seguir para a instalação.</p>

            <div id="imagens">
                <img src="./images/postgre8.png" alt="postgreSQL"/>
            </div>

            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}

function JRE(object){
    validaStyle();
    selected = object;
    let html = "";
    html += `<label id="title">Requisitos</label>
            <label class="sub-title">Java</label>
            <p>Para conseguir executar o programa é necessário que o JRE 1.8.0 esteja instalado na máquina, 
            para baixar acesse esse <a href="https://www.enterprisedb.com/downloads/postgres-postgresql-downloads">link</a>, 
            e clique para baixar como indicado a baixo</p>
            <div id="imagens">
                <img src="./images/javadownload.png" alt="JRE download"/>
            </div>

            <p>Execute o programa baixado e siga as instruções como indicado abaixo</p>

            <div id="imagens">
                <img src="./images/java1.png" alt="JRE download"/>
            </div>

            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}