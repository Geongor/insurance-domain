$( document ).ready(function() {

    var insurancePayment = 0;



    $("#policyForm").on("mousedown", "#sbmt_btn" ,function (e){

        if (validForm()){
        var formData = $("#policyForm").serializeArray();
                formData[formData.length] = {name: "InsurancePayment", value: insurancePayment};

                console.log(formData);

                $.ajax ({
                    url: "/property_policy_creation",
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
            //изменённое место
            let div = document.createElement('div');
            div.innerHTML = "<input type='text' id='name' name='props'>InsuranceSum: <input type='number' min='1' step='any' name='sum' id='sum'><br>";
            content.append(div);
        });
    });

    $("#properties").on("change", "#sum" ,function (e){

        var sums = $("#policyForm").serializeArray();


        $.ajax ({
                    url: "/countInsurancePayment",
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
            let names = document.querySelectorAll("#name");
            let values = document.querySelectorAll("#sum");

            for(let i = 0; i < names.length; i++){
                if(names[i].value !== "" && values[i].value === ""){
                    alert("Неверно заполненная форма");
                    return false;
                }
                if(names[i].value === "" && values[i].value !== ""){
                    alert("Неверно заполненная форма");
                    return false;
                }
            }

            let startDate = document.querySelector("#startDate");
            let endDate = document.querySelector("#endDate");


            if(startDate.value > endDate.value || startDate.value === "" || endDate.value === "") {
                alert("Дата вступления в силу не модет быть после даты истечения договора. Поля не должны быть пустые");
                return false;
            }

            if(dateOfSigning.value > startDate.value) {
                alert("Дата подписания не может быть позже вступления договора в силу");
                return false;
            }
            return true;
        }

});


