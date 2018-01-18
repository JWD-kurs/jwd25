var pivnicaApp = angular.module("pivnicaApp", ['ngRoute']);

pivnicaApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/',{
        templateUrl: '/app/html/partial/piva.html'
    }).when('/piva/edit/:id',{
        templateUrl: '/app/html/partial/edit-pivo.html'
    }).otherwise({
        redirectTo: '/'
    });
}]);

pivnicaApp.controller("pivaCtrl", function($scope, $http, $location){

	var baseUrlPivare = "/api/pivare";
    var baseUrlPiva = "/api/piva";
    
    $scope.pageNum = 0;
    $scope.totalPages = 0;

    $scope.pivare = [];
    $scope.piva = [];

    $scope.novoPivo = {};
    $scope.novoPivo.naziv = "";
    $scope.novoPivo.vrsta = "";
    $scope.novoPivo.procenatAlkohola = "";
    $scope.novoPivo.ibu = "";
    $scope.novoPivo.kolicina = "";
    $scope.novoPivo.pivaraId = "";


    $scope.trazenoPivo = {};
    $scope.trazenoPivo.naziv = "";
    $scope.trazenoPivo.minibu = "";
    $scope.trazenoPivo.maxibu = "";
    $scope.trazenoPivo.pivaraId = "";

    var getPiva = function(){

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;

        if($scope.trazenoPivo.naziv != ""){
            config.params.naziv = $scope.trazenoPivo.naziv;
        }

        if($scope.trazenoPivo.minibu != ""){
            config.params.minibu = $scope.trazenoPivo.minibu;
        }
        
        if($scope.trazenoPivo.maxibu != ""){
            config.params.maxibu = $scope.trazenoPivo.maxibu;
        }

        if($scope.trazenoPivo.pivaraId != ""){
            config.params.pivaraId = $scope.trazenoPivo.pivaraId;
        }

        $http.get(baseUrlPiva, config)
            .then(function success(data){
                $scope.piva = data.data;
                $scope.totalPages = data.headers('totalPages');

            });
    };

    var getPivare = function(){

        $http.get(baseUrlPivare)
            .then(function success(data){
                $scope.pivare = data.data;
            });

    };

    getPiva();
    getPivare();
   

    $scope.nazad = function(){
        if($scope.pageNum > 0) {
            $scope.pageNum = $scope.pageNum - 1;
            getPiva();
        }
    };

    $scope.napred = function(){
        if($scope.pageNum < $scope.totalPages - 1){
            $scope.pageNum = $scope.pageNum + 1;
            getPiva();
        }
    };

    $scope.dodaj = function(){
        $http.post(baseUrlPiva, $scope.novoPivo)
            .then(
            	function success(data){
	            	getPiva();
	
	            	$scope.novoPivo.naziv = "";
	                $scope.novoPivo.vrsta = "";
	                $scope.novoPivo.procenatAlkohola = "";
	                $scope.novoPivo.ibu = "";
	                $scope.novoPivo.kolicina = "";
	                $scope.novoPivo.pivaraId = "";
            	},
            	function error(data){
            		alert("Neuspesno dodavanje piva!");
            	}
            );
    };

    $scope.trazi = function () {
        $scope.pageNum = 0;
        getPiva();
    }

    $scope.izmeni = function(id){
        $location.path('/piva/edit/' + id);
    }

    $scope.obrisi = function(id){
        $http.delete(baseUrlPiva + "/" + id).then(
            function success(data){
            	getPiva();
            },
            function error(data){
                alert("Neuspesno brisanje!");
            }
        );
    }
    
    $scope.kupi = function(id){
    	$http.post(baseUrlPiva + "/" + id + "/kupovina").then(
    		function success(data){
    			alert("Pivo je uspesno kupljeno.");
    			getPiva();
    		},
    		function error(data){
    			alert("Nije uspela kupovina piva.")
    		}
    	)
    }
});

pivnicaApp.controller("editPivaCtrl", function($scope, $http, $routeParams, $location){

    var baseUrlPiva = "/api/piva";

    $scope.staroPivo = null;

    var getStaroPivo = function(){

        $http.get(baseUrlPiva + "/" + $routeParams.id)
            .then(
            	function success(data){
            		$scope.staroPivo = data.data;
            	},
            	function error(data){
            		alert("Neušpesno dobavljanje piva.");
            	}
            );

    }
    getStaroPivo();
    
    $scope.izmeni = function(){
        $http.put(baseUrlPiva + "/" + $scope.staroPivo.id, $scope.staroPivo)
            .then(
        		function success(data){
        			alert("Uspešno izmenjen objekat!");
        			$location.path("/");
        		},
        		function error(data){
        			alert("Neuspešna izmena piva.");
        		}
            );
    }
});












