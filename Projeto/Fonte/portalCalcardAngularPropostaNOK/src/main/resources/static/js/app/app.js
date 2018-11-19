var app = angular.module('crudProposta',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/portalCalcardRestService',
    PROPOSTA_SERVICE_API : 'http://localhost:8080/portalCalcardRestService/api/proposta/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'PropostaController',
                controllerAs:'ctrl',
                resolve: {
                    Propostas: function ($q, PropostaService) {
                        console.log('Load all Propostas');
                        var deferred = $q.defer();
                        PropostaService.loadAllPropostas().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

