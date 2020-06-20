$(function() {
	$(document).on("click", "a#listar_contato", function() {
		getListarContato();
	});
	$(document).on("click", "a#novo_contato", function() {
		getFormularioInclusao();
	});
	$(document).on("click", "button#salvar_contato", function() {
		if (validaDados()) {
			salvarContato();
		}
	});
	$(document).on("click", "a#confirma_excluir", function() {
		confirmaExclusao(this);
	});
	$(document).on("click", "button#excluir", function() {
		excluirContato();
	});
	$(document).on("click", "a#editar_contato", function() {
		getEditarContato(this);
	});
	$(document).on("click", "button#salvar_contato_edicao", function() {
		if (validaDados()) {
			salvarContatoEdicao();
		}
	});
	$(document).on("focus", "input#nome", function() {
		$("input").removeClass('error').next('span').remove();
	});
});

function getListarContato() {
	$('#carregando').show();
	$.post('crud.php', {
		acao : 'listar_contato'
	}, function(dados) {
		criaListagemTabela(dados);
		$('#carregando').hide();
	}, "json");
}

function criaListagemTabela(jsonDados) {
	var tabela = '<div class="table-responsive">';
	tabela += '<table class="table table-hover table-bordered"><thead><tr>';
	tabela += '<th scope="col">#</th><th scope="col">Nome</th>'
	tabela += '<th scope="col">Telefone</th><th scope="col">E-mail</th>';
	tabela += '<th scope="col">Endereço</th> <th scope="col">Site</th>   <th scope="col"></th><th scope="col"></th></tr></thead><tbody>';
	$
			.each(
					jsonDados,
					function(indice, contato) {
						tabela += '<tr>';
						tabela += '<td>' + contato.id + '</td>';
						tabela += '<td>' + contato.nome + '</td>';
						tabela += '<td>' + contato.telefone + '</td>';
						tabela += '<td>' + contato.email + '</td>';
						tabela += '<td>' + contato.endereco + '</td>';
						tabela += '<td>' + contato.site + '</td>';
						tabela += '<td><a href="javascript:void(0);" id="editar_contato" contato_id="'
								+ contato.id
								+ '" class="btn btn-success btn-xs"><i class="glyphicon glyphicon-pencil"></i></a></td>';
						tabela += '<td><a href="javascript:void(0);" id="confirma_excluir" contato_id="'
								+ contato.id
								+ '" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-trash"></i></a></td>';
						tabela += '</tr>';
					});
	tabela += '</tbody></table></div>';
	$('div#conteudo').html(tabela);
}

function getFormularioInclusao() {
	var form = '<form class="form-horizontal">';
	form += '<div class="form-group">';
	form += '<label for="nome" class="col-sm-2 control-label glyphicon glyphicon-user"></label>';
	form += '<div class="col-sm-8">';
	form += '<input type="text" class="form-control" id="nome" placeholder="Nome*" required>';
	form += '</div>';
	form += '</div>';
	
	form += '<div class="form-group">';
	form += '<label for="telefone" class="col-sm-2 control-label glyphicon glyphicon-phone"></label>';
	form += '<div class="col-sm-8">';
	form += '<input type="tel" class="form-control" id="telefone" placeholder="Telefone">';
	form += '</div>';
	form += '</div>';
	form += '<div class="form-group">';
	form += '<label for="email" class="col-sm-2 control-label glyphicon glyphicon-envelope"></label>';
	form += '<div class="col-sm-8">';
	form += '<input type="email" class="form-control" id="email" placeholder="E-mail">';
	form += '</div>';
	form += '</div>';
	form += '<div class="form-group">';
	form += '<label for="endereco" class="col-sm-2 control-label glyphicon glyphicon-home"></label>';
	form += '<div class="col-sm-8">';
	form += '<textarea class="form-control" rows="3" id="endereco" placeholder="Endereço"></textarea>';
	form += '</div>';
	form += '</div>';
	form += '<div class="form-group">';
	form += '<label for="site" class="col-sm-2 control-label glyphicon glyphicon-globe"></label>';
	form += '<div class="col-sm-8">';
	form += '<input type="url" class="form-control" id="site" placeholder="Site">';
	form += '</div>';
	form += '</div>';
	form += '<div class="form-group">';
	form += '<div class="col-sm-offset-2 col-sm-10">';
	form += '<button type="button" id="salvar_contato" class="btn btn-primary">Salvar</button>';
	form += '</div>';
	form += '</div>';
	form += '</form>';
	$('div#conteudo').html(form);
}

function salvarContato() {
	$('#carregando').show();
	var Contato = new Object();
	Contato.nome = $('input#nome').val();
	Contato.telefone = $('input#telefone').val();
	Contato.email = $('input#email').val();
	Contato.endereco = $('textarea#endereco').val();
	Contato.site = $('input#site').val();
	var contatoJson = JSON.stringify(Contato);
	$.post('crud.php', {
		acao : 'adicionar_contato',
		contato : contatoJson
	}, function(dado) {
		getListarContato();
		$('#carregando').hide();
	}, "json");
}

function confirmaExclusao(elemento) {
	$("#excluir_contato_modal").modal("show");
	$("#excluir_contato_modal input#contato_id").val(
			$(elemento).attr('contato_id'));
}
function excluirContato() {
	var Contato = new Object();
	Contato.id = $("#excluir_contato_modal input#contato_id").val();
	var contatoJson = JSON.stringify(Contato);
	$.post('crud.php', {
		acao : 'excluir_contato',
		contato : contatoJson
	}, function(dado) {
		getListarContato();
		$("#excluir_contato_modal").modal("hide");
	}, "json");
}

function getEditarContato(elemento) {
	var Contato = new Object();
	Contato.id = $(elemento).attr('contato_id');
	var contatoJson = JSON.stringify(Contato);
	$('#carregando').show();
	$.post('crud.php', {
		acao : 'buscar_contato',
		contato : contatoJson
	}, function(dado) {
		getFormularioEdicao(dado);
		$('#carregando').hide();
	}, "json");
}
function getFormularioEdicao(jsonDado) {
	var form = '<form class="form-horizontal">';
	$
			.each(
					jsonDado,
					function(indice, contato) {
						form += '<div class="form-group">';
						form += '<input type="hidden" id="contato_id" value="'
								+ contato.id + '">';
						form += '<label for="nome" class="col-sm-2 control-label glyphicon glyphicon-user"></label>';
						form += '<div class="col-sm-8">';
						form += '<input type="text" class="form-control" id="nome" placeholder="Nome*" value="'
								+ contato.nome + '" required>';
						form += '</div>';
						form += '</div>';
						
						form += '<div class="form-group">';
						form += '<label for="telefone" class="col-sm-2 control-label glyphicon glyphiconphone"></label>';
						form += '<div class="col-sm-8">';
						form += '<input type="tel" class="form-control" id="telefone" placeholder="Telefone" value="'
								+ contato.telefone + '">';
						form += '</div>';
						form += '</div>';
						form += '<div class="form-group">';
						form += '<label for="email" class="col-sm-2 control-label glyphicon glyphiconenvelope"></label>';
						form += '<div class="col-sm-8">';
						form += '<input type="email" class="form-control" id="email" placeholder="E-mail" value="'
								+ contato.email + '">';
						form += '</div>';
						form += '</div>';
						form += '<div class="form-group">';
						form += '<label for="endereco" class="col-sm-2 control-label glyphicon glyphicon-home"></label>';
						form += '<div class="col-sm-8">';
						form += '<textarea class="form-control" rows="3" id="endereco" placeholder="Endereço">'
								+ contato.endereco + '</textarea>';
						form += '</div>';
						form += '</div>';
						
						form += '<div class="form-group">';
						form += '<label for="site" class="col-sm-2 control-label glyphicon glyphiconenvelope"></label>';
						form += '<div class="col-sm-8">';
						form += '<input type="url" class="form-control" id="site" placeholder="Site" value="'
								+ contato.site + '">';
						form += '</div>';
						form += '</div>';
						form += '<div class="form-group">';
						form += '<div class="col-sm-offset-2 col-sm-10">';
						form += '<button type="button" id="salvar_contato_edicao" class="btn btnprimary">Salvar</button>';
						form += '</div>';
						form += '</div>';
					});
	form += '</form>';
	$('div#conteudo').html(form);
}
function salvarContatoEdicao() {
	$('#carregando').show();
	var Contato = new Object();
	Contato.id = $('input#contato_id').val();
	Contato.nome = $('input#nome').val();
	Contato.telefone = $('input#telefone').val();
	Contato.email = $('input#email').val();
	Contato.endereco = $('textarea#endereco').val();
	Contato.site = $('input#site').val();
	var contatoJson = JSON.stringify(Contato);
	$.post('crud.php', {
		acao : 'editar_contato',
		contato : contatoJson
	}, function(dado) {
		getListarContato();
		$('#carregando').hide();
	}, "json");
}

function validaDados() {
	if ($("#nome").val().trim().length == 0) {
		$("#nome").addClass('error').after(
				'<span class="error">Por favor, preencha esse campo.</span>');
		return false;
	}
	return true;
}
