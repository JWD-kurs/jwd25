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
	
	$scope.deleteActivity = function (id){
		$http.delete(baseUrl + "/" + id).then(
			function success(answ){
				getActivities();
			},
			function error(answ){
				alert("Couldn't delete activity!");
			}	
		);
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

wafepa.controller("standoviCtrl", function($scope, $http){
	
	var baseUrl = "/api/standovi";
	var baseUrlSajam = "/api/sajmovi";
	
	$scope.standovi = [];
	$scope.sajmovi = [];
	
	$scope.noviStand = {};
	$scope.noviStand.zakupac = "";
	$scope.noviStand.povrsina = "";
	$scope.noviStand.sajamId = 0;
	
	$scope.trazeniStand = {};
	$scope.trazeniStand.zakupac = "";
	$scope.trazeniStand.minP = "";
	$scope.trazeniStand.maxP = "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	
	var getStandovi = function(){
		
		var conf = {params: {}};
		
		if($scope.trazeniStand.zakupac != ""){
			conf.params.zakupac = $scope.trazeniStand.zakupac;
		}
		if($scope.trazeniStand.minP != ""){
			conf.params.minP = $scope.trazeniStand.minP;
		}
		if($scope.trazeniStand.maxP != ""){
			conf.params.maxP = $scope.trazeniStand.maxP;
		}
		
		conf.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(baseUrl, conf);
		promise.then(
			function uspeh(odg){
				$scope.standovi = odg.data;
				$scope.totalPages = odg.headers("totalPages");
			},
			function neuspeh(odg){
				alert("Something went wrong!");
			}
		);
	}
	
	var getSajmovi = function(){
		
		var promise = $http.get(baseUrlSajam);
		promise.then(
			function uspeh(odg){
				$scope.sajmovi = odg.data;
			},
			function neuspeh(odg){
				alert("Couldn't fetch sajam's!");
			}
		)
		
	}
	
	getStandovi();
	getSajmovi();
	
	$scope.addStand = function(){
		
		var promise = $http.post(baseUrl, $scope.noviStand);
		promise.then(
			function success(answ){
				getStandovi();
			},
			function error(answ){
				alert("Something went wrong!");
			}
		);
	}
	
	$scope.changeHappened = function(){
		alert("Hello!");
	}
	
	$scope.search = function(){	
		getStandovi();	
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getStandovi();
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
		.when('/standovi', {
			templateUrl : '/app/partial/standovi.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);