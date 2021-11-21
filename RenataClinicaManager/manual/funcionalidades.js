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
    html += `<label id="title">Funcionarlidades</label>
            <label class="sub-title">Recebimento</label>
            <p>A tela de recebimento é composta por duas abas, uma para contas a receber e outra para contas recebidas</p>
            <div id="imagens">
                <img src="./images/recebimentoAbas.png" alt="Abas do Recebimento"/>
            </div>

            <p>Para cadastrar um novo recebimento é necessário clicar no botão "Gerar Recebimento", então, irá abrir uma tela nova que servirá 
            para colocar as informações do paciente e do tratamento, sendo possível também editar o valor a ser cobrado.</p>
            <div id="imagens">
                <img src="./images/recebimentoAdd.png" alt="Adicionar Recebimento"/>
                <img src="./images/recebimentoAddConfirmar.png" alt="Adicionar Recebimento"/>
            </div>

            <p>Para confirmar o recebimento de uma conta é necessário seleciona-la e clicar no botão "Receber", irá aparecer uma tela de confirmação
            , então, é só clicar em sim. (é possível editar o valor e data do recebimento)</p>
            <div id="imagens">
                <img src="./images/recebimentoReceber.png" alt="Receber"/>
                <img src="./images/recebimentoConfirmarReceber.png" alt="Confirmar Recebimento"/>
            </div>

            <p>Para estornar uma conta primeiramente clique na aba de contas recebidas, selecione a conta desejada e clique em estornar, abrirá uma 
            tela de confirmação, confirme e a conta será estornada</p>
            <div id="imagens">
                <img src="./images/recebimentoEstornar.png" alt="Receber"/>
                <img src="./images/recebimentoEstornarConfirmar.png" alt="Confirmar Recebimento"/>
            </div>
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
    html += `<label id="title">Funcionalidades</label>
            <label class="sub-title">Compra</label>
            <p>Para registrar uma compra primeiramente é necessário selecionar um fornecedor.</p>
            <div id="imagens">
                <img src="./images/compraFornecedor.png" alt="Fornecedor da Compra"/>
            </div>

            <p>Posteriormente é necessário adicionar os itens da compra como mostrado abaixo, colocando o nome do produto o preço e aquantidade 
            e clicando em adicionar. (é possível digitar um produto que não esteja cadastrado, ele será cadastrado durante a conclusão da compra)</p>
            <div id="imagens">
                <img src="./images/compraMaterial.png" alt="Materiais da Compra"/>
            </div>

            <p>Caso queira remover um material dos itens da compra é necessário seleciona-lo e clicar em remover como mostrado abaixo.</p>
            <div id="imagens">
                <img src="./images/compraRemoveMaterial.png" alt="Reveção de Materiais"/>
            </div>

            <p>Após inserir todos os produtos clique no botão de confirmação mostrado abaixo</p>
            <div id="imagens">
                <img src="./images/compraConfirmar.png" alt="Confirmar Compra"/>
            </div>

            <p>Após a confirmação aparecerá a tela de parcelas, caso tenha dividido a compra, então selecione a data do primeiro vencimento 
            e quantas parcelas deverão ser geradas.</p>
            <div id="imagens">
                <img src="./images/compraParcelas.png" alt="Tela de Parcelas"/>
            </div>

            <p>Todos os campos são editáveis, mas caso queira resetar aos valores padrões de cada parcela (data e valor), clique no botão indicado</p>
            <div id="imagens">
                <img src="./images/compraResetar.png" alt="Resetar Parcelas"/>
            </div>

            <p>Quando estiver tudo de acordo com a compra feita clique no botão de confirmação, como indicado abaixo</p>
            <div id="imagens">
                <img src="./images/compraConfirmarParcelas.png" alt="Confirmar Operação de Compra"/>
            </div>

            <p>Após a operação terminar aparecerá a compra na tela de compras como indicado.</p>
            <div id="imagens">
                <img src="./images/compraSalva.png" alt="Compra Salva"/>
            </div>
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
    html += `<label id="title">Funcionalidades</label>
            <label class="sub-title">Pagamentos</label>
            <p>A tela de pagamento é composta por duas abas, uma para contas a pagar e outra para contas pagas</p>
            <div id="imagens">
                <img src="./images/pagamentoAbas.png" alt="Abas do Pagamento"/>
            </div>

            <p>Para cadastrar um novo pagamento é necessário clicar no botão "Adicionar despesa", então, irá abrir uma tela nova que servirá 
            para colocar as informações da despesa</p>
            <div id="imagens">
                <img src="./images/pagamentoAdd.png" alt="Adicionar Pagamento"/>
                <img src="./images/pagamentoAddConfirmar.png" alt="Adicionar Pagamento"/>
            </div>

            <p>Para liquidar uma conta é necessário seleciona-la e clicar no botão "Liquidar", irá aparecer uma tela de confirmação
            , então, é só clicar em sim. (é possível editar o valor e data do pagamento)</p>
            <div id="imagens">
                <img src="./images/pagamentoLiquidar.png" alt="Liquidar"/>
                <img src="./images/pagamentoLiquidarConfirmar.png" alt="Liquidar"/>
            </div>

            <p>Para estornar uma conta primeiramente clique na aba de contas pagas, selecione a conta desejada e clique em estornar, abrirá uma 
            tela de confirmação, confirme e a conta será estornada</p>
            <div id="imagens">
                <img src="./images/pagamentoEstornar.png" alt="Receber"/>
                <img src="./images/pagamentoEstornarConfirmar.png" alt="Confirmar Recebimento"/>
            </div>
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
    html += `<label id="title">Funcionalidades</label>
            <label class="sub-title">Atendimento</label>
            <p>Para registrar um atendimento primeiramente é necessário primeiramente clicar em um agendamento que esteja no dia atual ou anterior
            e clicar em "Registrar Atendimento"</p>
            <div id="imagens">
                <img src="./images/atendimentoDireito.png" alt="Opção Atendimento"/>
            </div>

            <p>Então aparecerá uma tela com as informações do paciente, selecione então o dentista que atendeu esse paciente</p>
            <div id="imagens">
                <img src="./images/atendimentoDentista.png" alt="Dentista do Atendimento"/>
            </div>

            <p>Selecione os dentes que foram mechidos no procedimento</p>
            <div id="imagens">
                <img src="./images/atendimentoDente.png" alt="Dentes do Atendimento"/>
            </div>

            <p>Para adicionar os materiais utilizados selecione o material no campo "Material", adicione a quantidade utilizada e clique no botão 
            indicado</p>
            <div id="imagens">
                <img src="./images/atendimentoAddMaterial.png" alt="Materiais do Atendimento"/>
            </div>

            <p>Escreva observações do atendimento caso queira e clique no botão de confirmação para salvar o atendimento</p>
            <div id="imagens">
                <img src="./images/atendimentoObservacoes.png" alt="Observações do Atendimento"/>
            </div>
            `
    object.style.color = 'rgb(16, 66, 158)';
    object.style.fontWeight = 'bold';
    document.querySelector('#conteudo').innerHTML = html;
    document.querySelector('#conteudo').style.visibility = 'visible';
}