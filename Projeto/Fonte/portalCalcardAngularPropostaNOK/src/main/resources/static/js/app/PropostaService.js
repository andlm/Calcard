'use strict';

angular.module('crudApp').factory('PropostaService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllPropostas: loadAllPropostas,
                getAllPropostas: getAllPropostas,
                getProposta: getProposta,
                createProposta: createProposta,
                updateProposta: updateProposta,
                removeProposta: removeProposta
            };

            return factory;

            function loadAllPropostas() {
                var deferred = $q.defer();
                $http.get(urls.PROPOSTA_SERVICE_API)
                    .then(
                        function (response) {
                            $localStorage.Propostas = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllPropostas(){
                return $localStorage.Propostas;
            }

            function getProposta(id) {
                var deferred = $q.defer();
                $http.get(urls.PROPOSTA_SERVICE_API + id)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createProposta(Proposta) {
                var deferred = $q.defer();
                $http.post(urls.PROPOSTA_SERVICE_API, Proposta)
                    .then(
                        function (response) {
                            loadAllPropostas();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Proposta : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateProposta(Proposta, id) {
                var deferred = $q.defer();
                $http.put(urls.PROPOSTA_SERVICE_API + id, Proposta)
                    .then(
                        function (response) {
                            loadAllPropostas();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeProposta(id) {
                var deferred = $q.defer();
                $http.delete(urls.PROPOSTA_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllPropostas();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);