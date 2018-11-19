'use strict';

angular.module('crudProposta').controller('PropostaController',
    ['PropostaService', '$scope',  function( PropostaService, $scope) {

        var self = this;
        self.Proposta = {};
        self.Propostas=[];

        self.submit = submit;
        self.getAllPropostas = getAllPropostas;
        self.createProposta = createProposta;
        self.updateProposta = updateProposta;
        self.removeProposta = removeProposta;
        self.editProposta = editProposta;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.Proposta.id === undefined || self.Proposta.id === null) {
                console.log('Saving New Proposta', self.Proposta);
                createProposta(self.Proposta);
            } else {
                updateProposta(self.Proposta, self.Proposta.id);
                console.log('Proposta updated with id ', self.Proposta.id);
            }
        }

        function createProposta(Proposta) {
            console.log('About to create Proposta');
            PropostaService.createProposta(Proposta)
                .then(
                    function (response) {
                        console.log('Proposta created successfully');
                        self.successMessage = 'Proposta created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.Proposta={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Proposta');
                        self.errorMessage = 'Error while creating Proposta: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateProposta(Proposta, id){
            console.log('About to update Proposta');
            PropostaService.updateProposta(Proposta, id)
                .then(
                    function (response){
                        console.log('Proposta updated successfully');
                        self.successMessage='Proposta updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Proposta');
                        self.errorMessage='Error while updating Proposta '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeProposta(id){
            console.log('About to remove Proposta with id '+id);
            PropostaService.removeProposta(id)
                .then(
                    function(){
                        console.log('Proposta '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing Proposta '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllPropostas(){
            return PropostaService.getAllPropostas();
        }

        function editProposta(id) {
            self.successMessage='';
            self.errorMessage='';
            PropostaService.getProposta(id).then(
                function (Proposta) {
                    self.Proposta = Proposta;
                },
                function (errResponse) {
                    console.error('Error while removing Proposta ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.Proposta={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);