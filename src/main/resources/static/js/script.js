$(document).ready(function() {
		  $(".tab-link").click(function() {
			var tabId = $(this).attr("data-tab");
			
			$(".tab-link").removeClass("active");
			$(".tab-content").removeClass("active");
			
			$(this).addClass("active");
			$("#" + tabId).addClass("active");
		  });
		});