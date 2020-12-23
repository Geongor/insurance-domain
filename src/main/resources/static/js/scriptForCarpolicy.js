$( document ).ready(function() {

    var insurancePayment = 0;



    $("#policyForm").on("mousedown", "#sbmt_btn" ,function (e){

        if (validForm()){
        var formData = $("#policyForm").serializeArray();
                formData[formData.length] = {name: "InsurancePayment", value: insurancePayment};

                console.log(formData);

                $.ajax ({
                    url: "/car_policy_creation",
                    type: "POST",
                    data: formData,
                    dataType: "json",
                    success: getResult
                });
        }


    });

    $("#propertiesDiv").on("mousedown", "#addField" ,function (e){

        $("#properties").html(function(index, oldHtml){

            let content = document.querySelector("#properties"); //твой label properties
            let id = $("#properties").attr("data-n");
            id++;
            $("#properties").attr("data-n", id);
            let div = document.createElement('div');
            div.innerHTML = "<input type='text' id='name' name='persons'><br>";
            content.append(div);
        });
    });

    $("#cost").on("change", "#carCost" ,function (e){

        var sums = $("#policyForm").serializeArray();

        console.log("insurancePayment");
        $.ajax ({
                    url: "/countCarInsurancePayment",
                    type: "POST",
                    data: sums,
                    dataType: "json",
                    success: printSums
                });
    });


    function printSums(response){

        insurancePayment = response["insurancePayment"];

        $("#iPay").html(insurancePayment);
    }

    function getResult(response){

        if (response["status"] == "ok"){

            alert("Done!");
        } else {

            alert("fail(")
        }
    }

    function validForm(){

            let dateOfSigning = document.querySelector("#dateOfSigning");
            let startDate = document.querySelector("#startDate");
            let endDate = document.querySelector("#endDate");
            let carBrandAndModel = document.querySelector("#carBrandAndModel");
            let carCost = document.querySelector("#carCost");
            let vehicleIdentificationNumber = document.querySelector("#vehicleIdentificationNumber");
            let vehicleNumber = document.querySelector("#vehicleNumber");

            if(carBrandAndModel.value === "" || carCost.value === "" || vehicleIdentificationNumber.value === "" || vehicleNumber.value === ""){
                alert("Не все поля заполнены");
                return false
            }

            if(startDate.value > endDate.value || startDate.value === "" || endDate.value === "") {
                alert("Дата вступления в силу не модет быть после даты истечения договора. Поля не должны быть пустые");
                return false;
            }

            if(dateOfSigning.value > startDate.value) {
                alert("Дата заключения договра не может быть позже начала срока страховки");
                return false;
            }
            return true;
        }
});