<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="min-height: 100vh; padding-top: 100px">
    <form action="${pageContext.request.contextPath}/app/save-test.html" method="post">

        <h3>piano: </h3>
        <div id="piano">
            <div >
                <div style="margin-left: 0">
                    <input id="name-1" name="name-1">
                    <input id="price-1" name="price-1">
                </div>
            </div>
        </div>

        <input type="button" onclick="onAddPiano()"
               value="addPiano">
        <input type="submit" class="col-12 paper-btn btn-block btn-success-outline" value="save">
    </form>
</div>

<script>
    let pianoNumber = 2;
    const pianos = document.querySelector("#pianos");

    const createPianoFormGroup = () => {
        const formGroup = document.createElement("div");
        formGroup.className = "row form-group";
        formGroup.innerHTML =
            `
                <div style="margin-left: 0">
                    <input id="name-` + pianoNumber + `" name="piano-` + pianoNumber + `>
                    <input id="price-` + pianoNumber + `" name="price-` + pianoNumber + `>
                </div>

                <div class="col-2">
                    <input class="input-block" type="button" onclick="onRemovePianoGroup(` + pianoNumber + `)" value="remove">
                </div>
            `;
        formGroup.setAttribute("data-piano-number", pianoNumber);

        pianos.appendChild(formGroup);
        pianoNumber += 1;
    }

    const onAddPiano = () => {
        createPianoFormGroup();
    }

    const onRemovePianoGroup = (number) => {
        const nodeToRemove = Array.from(pianos.children).find(q => q.dataset.pianoNumber === number.toString());
        pianos.removeChild(nodeToRemove);
    }
</script>


</body>
</html>
