function getID() {
    var width = window.outerWidth;
    var height = window.outerHeight;
    var answer = "";
 
    if(height == 600 && width == 786)
        answer = "id1";
    else if(height == 350 && width == 300)
        answer = "id2";
    else if(height == 100 & width == 100)
        answer = "id3";
    else
        answer = "id4";

    document.write(width);

    return answer;
}