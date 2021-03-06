/**
 * 
 */
function initSideNav(){
	$(".button-collapse").sideNav({
	  menuWidth: 85, // Default is 240
	  edge: 'left', // Choose the horizontal origin
	  closeOnClick: false // Closes side-nav on <a> clicks, useful for Angular/Meteor
	});
	$("#mdpConfirm").keyup(checkPasswordMatch);
	$("#mdp").keyup(checkPasswordMatch);
    $('.modal-trigger').leanModal();

}

function checkPasswordMatch(){
	var mdp = $("#mdp").val();
	var mdpConfirm = $("#mdpConfirm").val();
	if(mdp === mdpConfirm){
		 $("#mdp").removeClass("invalid");
		 $("#mdpConfirm").removeClass("invalid");
		 $("#mdp").addClass("valid");
		 $("#mdpConfirm").addClass("valid");
	}
	else {
		 $("#mdp").addClass("invalid");
		 $("#mdpConfirm").addClass("invalid");
		 $("#mdp").removeClass("valid");
		 $("#mdpConfirm").removeClass("valid");
	}
}

function checkOnSubmit(){
	
	var login = $("#loginEmploye").val();
	var nom = $("#nomEmploye").val();
	var mdp = $("#mdp").val();
	var mdpConfirm = $("#mdpConfirm").val();
	var error = false;
	if(nom.trim() == ""){
		$('#messageErreur').html("Le nom ne peut etre vide");
		error = true;
	}
	else if(login.trim() == ""){
		$('#messageErreur').html("Le login ne peut etre vide");
		error = true;
	}
	else if(mdp.trim() == ""){
		$('#messageErreur').html("Le mot de passe ne peut etre vide");
		error = true;
	}
	else if(mdpConfirm.trim() == ""){
		$('#messageErreur').html("La confirmation du mot de passe ne peut etre vide");
		error = true;
	}
	else if(mdp != mdpConfirm){
		$('#messageErreur').html("Le mot de passe et la confirmation sont diferents");
		error = true;
	}
	
	if(error == true){
		$('#error').openModal();
	}
	else{
		$('#formEmploye').submit();
	}
}

function checkOnArticle(){
	
	var login = $("#nomArticle").val();
	var poid = $("#poidArticle").val();
	var error = false;
	if(login.trim() == ""){
		$('#messageErreur').html("Le nom ne peut etre vide");
		error = true;
	}
	else if(poid.trim() == ""){
		$('#messageErreur').html("Le poid ne peut etre vide");
		error = true;
	}
	
	if(error == true){
		$('#error').openModal();
	}
	else{
		$('#formArticle').submit();
	}
}

function addArticleAjax(idArticle,idCommande,url){
	$("#qttTraite_" + idArticle).html(parseInt($("#qttTraite_" + idArticle).html()) + 1);
	$("#poidTotal_" + idArticle).html(parseInt($("#qttTraite_" + idArticle).html()) * parseInt($("#poidArticle_"+ idArticle).html()));
	sendAjax(idArticle,idCommande,$("#qttTraite_" + idArticle).html(),url);
}

function subtractArticleAjax(idArticle,idCommande,url){
	$("#qttTraite_" + idArticle).html(parseInt($("#qttTraite_" + idArticle).html()) - 1);
	$("#poidTotal_" + idArticle).html(parseInt($("#qttTraite_" + idArticle).html()) * parseInt($("#poidArticle_"+ idArticle).html()));
	sendAjax(idArticle,idCommande,$("#qttTraite_" + idArticle).html(),url);
}

function sendAjax(idArticlePa,idCommandePa,qttPa,url){
	$.ajax({
		  url: url + "/GestionAjoutAjax",
		  type: "get", //send it through get method
		  data:{"idArticle":idArticlePa,"idCommande":idCommandePa,"qtt":qttPa},
		  success: function(response) {
		    //Do Something
		  },
		  error: function(xhr) {
		    //Do Something to handle error
		  }
	});
}

function uploadCSV(){
	$("#formCsv").submit();
}



$(document).ready(initSideNav);