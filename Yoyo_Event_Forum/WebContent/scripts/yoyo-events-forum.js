(function() {
	"use strict"
//	var host = "http://private-d5490-yoyoeventsforumapi.apiary-mock.com/";
	var host = "http://localhost:8080/Yoyo_Event_Forum/api/";
	// Create Account vars
	var userName = $("#username");
	var email = $("#email");
	var password = $("#password");
	var radioButtonMale = $("#radioButtonMale");
	var radioButtonFemale = $("#radioButtonFemale");
	var gender = "";
	var createAccButton = $("#createAccButton");

	// Create Post vars
	var title = $("#title");
	var radioButtonMeeting = $("#radioButtonMeeting");
	var radioButtonTournament = $("#radioButtonTournament");
	var postType = "";
	var city = $("#postCity");
	var date = $("#postDate");
	var time = $("#postTime");
	var description = $("#postDescription");
	var createPostButton = $("#createPostButton");
	var meetingsList = $("#meetingsList");
	var tournamentsList = $("#tournamentsList");
	
	
	displayPost();
	// Create Account
	createAccButton.click(function(event) {
		event.preventDefault();
		if ((userName.val() !== "") && (email.val() !== "")
				&& (password.val() !== "")) {
			if (radioButtonMale.is(":checked")) {
				// Male radio button is checked
				gender = radioButtonMale.val();
			} else if (radioButtonFemale.is(":checked")) {
				// Female radio button is checked
				gender = radioButtonFemale.val();
			}
		}
		
		$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : host + 'accounts',
			data : {
				"username": userName.val(),
				"password": password.val(),
				"email": email.val(),
				"sex": gender
			},
			success : function(data, textStatus, jQxhr) {
				console.log(textStatus);
			},
		});
	});
	
	// Create Post
	createPostButton.click(function(event) {
		event.preventDefault();
		if ((title.val() !== "") && (city.val() !== "")
				&& (date.val() !== "") && (time.val() !== "") && (description.val() !== "")) {
//			if (radioButtonMeeting.is(":checked")) {
//				// Meeting radio button is checked
//				postType = radioButtonMeeting.val();
//			} else if (radioButtonTournament.is(":checked")) {
//				// Tournament radio button is checked
//				postType = radioButtonTournament.val();
//			}
			postType = radioButtonMeeting.val();
		}
		
		$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : host + 'posts',
			data : {
//				"author": "Kaloyan",
				"type": postType,
				"title": title.val(),
				"place": city.val(),
				"date": date.val(),
				"time": time.val(),
				"description": description.val()
			},
			success : function(data, textStatus, jQxhr) {
				listPost(data);
			},
		});
	});
	
	function displayPost() {
		$.ajax(host + "posts", {
			method : "GET"
		}).then(function(posts) {
			$.each(posts, function() {
				listPost(this);
			})
		});
	}
	
	function listPost(post) {
		if(post.type === "meeting") {
			var newElement = $("<li/>");
			newElement.text(post.title);
			meetingsList.append(newElement);
		} else {
			var newElement = $("<li/>");
			newElement.text(post.title);
			tournamentsList.append(newElement);
		}
	}
	//List all meetings
	
})();