$(document).ready(function(){
	$(".close").prev().slideUp("slow");
          $(".feed").click(function(){
	          $(this).next().slideToggle("slow");
	      });
	      $(".close").click(function(){
	          $(this).prev().slideUp("slow");
	      });
	      
});