var gameModel;

$( document ).ready(function() {
  // Handler for .ready() called.
  $.getJSON("model", function( json ) {
  gameModel = json;
    console.log( "JSON Data: " + json );
   });
});

function placeShip() {
   console.log($( "#shipSelec" ).val());
   console.log($( "#rowSelec" ).val());
   console.log($( "#colSelec" ).val());
   console.log($( "#orientationSelec" ).val());

   //var menuId = $( "ul.nav" ).first().attr( "id" );
   var request = $.ajax({
     url: "/placeShip/"+$( "#shipSelec" ).val()+"/"+$( "#rowSelec" ).val()+"/"+$( "#colSelec" ).val()+"/"+$( "#orientationSelec" ).val(),
     method: "post",
     data: JSON.stringify(gameModel),
     contentType: "application/json; charset=utf-8",
     dataType: "json"
   });

   request.done(function( currModel ) {
     displayGameState(currModel);
     gameModel = currModel;

   });

   request.fail(function( jqXHR, textStatus ) {
     alert( "Request failed: " + textStatus );
   });
}




function fire(){
 console.log($( "#colFire" ).val());
   console.log($( "#rowFire" ).val());
//var menuId = $( "ul.nav" ).first().attr( "id" );
   var request = $.ajax({
     url: "/fire/"+$( "#colFire" ).val()+"/"+$( "#rowFire" ).val(),
     method: "post",
     data: JSON.stringify(gameModel),
     contentType: "application/json; charset=utf-8",
     dataType: "json"
   });

   request.done(function( currModel ) {
     displayGameState(currModel);
     gameModel = currModel;

   });

   request.fail(function( jqXHR, textStatus ) {
     alert( "Request failed: " + textStatus );
   });
    endTurn();
}

function scan(){
 console.log($( "#colScan" ).val());
   console.log($( "#rowScan" ).val());
//var menuId = $( "ul.nav" ).first().attr( "id" );
   var request = $.ajax({
     url: "/scan/"+$( "#colScan" ).val()+"/"+$( "#rowScan" ).val(),
     method: "post",
     data: JSON.stringify(gameModel),
     contentType: "application/json; charset=utf-8",
     dataType: "json"
   });

   request.done(function( currModel ) {
     displayGameState(currModel);
     gameModel = currModel;

   });

   request.fail(function( jqXHR, textStatus ) {
     alert( "Request failed: " + textStatus );
   });

    endTurn();
}


function log(logContents){
    console.log(logContents);
}

function displayGameState(gameModel){
$( '#MyBoard td'  ).css("background-color", "blue");
$( '#TheirBoard td'  ).css("background-color", "blue");

if(gameModel.scanResult){
alert("Scan found at least one Ship")}
else{
alert("Scan found no Ships")}

displayShip(gameModel.aircraftCarrier);
displayShip(gameModel.battleship);
displayShip(gameModel.cruiser);
displayShip(gameModel.destroyer);
displayShip(gameModel.submarine);

for (var i = 0; i < gameModel.computerMisses.length; i++) {
   $( '#TheirBoard #' + gameModel.computerMisses[i].Across + '_' + gameModel.computerMisses[i].Down ).css("background-color", "white");
}

for (var i = 0; i < gameModel.computerHits.length; i++) {
    $('#TheirBoard #' + gameModel.computerHits[i].Across + '_' + gameModel.computerHits[i].Down).css("background-color", "red");
}
/*
for (var i = 0; i < gameModel.computerSunk.length; i++) {
    $('#TheirBoard #' + gameModel.computerSunk[i].Across + '_' + gameModel.computerSunk[i].Down).css("background-color", "black");
}
*/
for (var i = 0; i < gameModel.playerMisses.length; i++) {
   $( '#MyBoard #' + gameModel.playerMisses[i].Across + '_' + gameModel.playerMisses[i].Down ).css("background-color", "white");
}

for (var i = 0; i < gameModel.playerHits.length; i++) {
   $( '#MyBoard #' + gameModel.playerHits[i].Across + '_' + gameModel.playerHits[i].Down ).css("background-color", "red");
}

/*for (var i = 0; i < gameModel.playerSunk.length; i++) {
    $( '#MyBoard #' + gameModel.playerSunk[i].Across + '_' + gameModel.playerSunk[i].Down ).css("background-color", "black");
}
*/
}



function displayShip(ship){
 startCoordAcross = ship.start.Across;
 startCoordDown = ship.start.Down;
 endCoordAcross = ship.end.Across;
 endCoordDown = ship.end.Down;
 sunk = ship.sunk;
// console.log(startCoordAcross);
 if(startCoordAcross > 0){
    if(startCoordAcross == endCoordAcross){
        for (i = startCoordDown; i <= endCoordDown; i++) {
            $( '#MyBoard #'+startCoordAcross+'_'+i  ).css("background-color", "grey");
        }
    } else {
        for (i = startCoordAcross; i <= endCoordAcross; i++) {
            $( '#MyBoard #'+i+'_'+startCoordDown  ).css("background-color", "grey");
        }
    }
 }
}


/* This function signals the end of the ship placement stage.
 * It should hide the place ship buttons. */
function endPlacement() {
    console.log("End ship placement");
    var x = document.getElementsByClassName("Placement");
     var i;
     for (i = 0; i < x.length; i++) {
         x[i].classList.add("hidden");
     }

     var y = document.getElementsByClassName("player");
          var i;
          for (i = 0; i < y.length; i++) {
              y[i].classList.remove("hidden");
          }
}

function startTurn() {
    var x = document.getElementsByClassName("player");
         var i;
         for (i = 0; i < x.length; i++) {
             x[i].classList.add("hidden");
         }
    var y = document.getElementsByClassName("enemy");
              var i;
              for (i = 0; i < y.length; i++) {
                  y[i].classList.remove("hidden");
              }
    var z = document.getElementsByClassName("shippyTipsEnemy");
                   var i;
                   for (i = 0; i < z.length; i++) {
                       z[i].classList.remove("hidden");
                   }
    var w = document.getElementsByClassName("shippyTipsPlayer");
                             var i;
                             for (i = 0; i < w.length; i++) {
                                 w[i].classList.add("hidden");
                             }
}

function endTurn() {
    var x = document.getElementsByClassName("enemy");
             var i;
             for (i = 0; i < x.length; i++) {
                 x[i].classList.add("hidden");
             }

    var y = document.getElementsByClassName("player");
                  var i;
                  for (i = 0; i < y.length; i++) {
                      y[i].classList.remove("hidden");
                  }

    var z = document.getElementsByClassName("shippyTipsEnemy");
                       var i;
                       for (i = 0; i < z.length; i++) {
                           z[i].classList.add("hidden");
                       }

    var w = document.getElementsByClassName("shippyTipsPlayer");
                          var i;
                          for (i = 0; i < w.length; i++) {
                              w[i].classList.remove("hidden");
                          }
}