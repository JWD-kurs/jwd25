var wafepa = angular.module("wafepaApp", ['ngRoute']);

wafepa.controller("activitiesCtrl", function ($scope, $http, $location){
	
	var baseUrl = "/api/activities";
	
	$scope.activities = [];
	
	$scope.newActivity = {};
	$scope.newActivity.name = "";
	
	$scope.gotActivities = false;
	
	var getActivities = function(){
	
		var promise = $http.get(baseUrl);
		promise.then(
			function success(answ){
				$scope.gotActivities = true;
				$scope.activities = answ.data;
			},
			function error(answ){
				alert("Something went wrong");
				console.log(answ);
			}
		);
	}
	
	getActivities();
	
	$scope.addActivity = function (){
		var promise = $http.post(baseUrl, $scope.newActivity);
		promise.then(
			function uspeh(odg){
				getActivities();
				$scope.newActivity.name = "";
			},
			function neuspeh(odg){
				alert("Something went wrong!");
				console.log(odg);
			}
		);
	}
	
	$scope.goToEdit = function(id){
		$location.path('/activities/edit/' + id)		
	}
	
});

wafepa.controller("editActivityCtrl", function($scope, $routeParams, $http, $location){
	
	var baseUrl = "/api/activities";
	var aid = $routeParams.aid;
	
	$scope.oldActivity = {};
	$scope.oldActivity.name = "";
	
	var getActivity = function(){
		
		$http.get(baseUrl + "/" + aid).then(
			function success(answ){
				$scope.oldActivity = answ.data;
			},
			function error(answ){
				alert('Something went wrong!');
			}
		);		
	}
	
	getActivity();
	
	
	$scope.edit = function(){
		
		$http.put(baseUrl + "/" + aid, $scope.oldActivity).then(
			function success(answ){
				$location.path("/activities");
			},
			function error(answ){
				alert('Something went wrong!');
			}
		);
	}
	
});


wafepa.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/partial/home.html'
		})
		.when('/activities', {
			templateUrl : '/app/partial/activities.html'
		})
		.when('/activities/edit/:aid', {
			templateUrl : '/app/partial/edit_activity.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);