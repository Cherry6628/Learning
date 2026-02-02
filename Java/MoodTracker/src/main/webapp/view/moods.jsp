<%@ page import="java.util.*, model.Mood"%>
<!doctype html>
<html>
<body>
	<h2>Mood History</h2>

	<table border="1">
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th>Mood</th>
		</tr>
		<%
		String[] moodArr = {"Very Sad", "Sad", "Neutral", "Happy", "Very Happy"};
		%>
		<tr>
			<td><input type="text" name=name></td>
			<td><input type="text" name=desc></td>
			<td><select name=mood>
					<%
					for (int i = 0; i < moodArr.length; i++) {
					%>
					<option value=<%=i%>><%=moodArr[i]%></option>
					<%
					}
					%>
			</select></td>
		</tr>
		<%
		List<Mood> moods = (List<Mood>) request.getAttribute("moods");
		for (Mood m : moods) {
		%>
		<tr>
			<td><%=m.getName()%></td>
			<td><%=m.getDescription()%></td>
			<td><%=moodArr[m.getMood()]%></td>
		</tr>
		<%
		}
		%>

	</table>
</body>
<script>
        const n = document.querySelector("input[name=name]");
        const d = document.querySelector("input[name=desc]");
        const m = document.querySelector("select[name=mood]");
        document.addEventListener("keydown", (e) => {
            if (event.key === "Enter") {
                const v = Number(m.value);
                if (
                    n.value &&
                    d.value &&
                    !Number.isNaN(v) &&
                    v >= 0 &&
                    v <= 4
                ) {
                    fetch("mood", {
                        method: "POST",
                        body: JSON.stringify({
                            name: n.value,
                            description: d.value,
                            mood: v,
                        }),
                    }).then(()=>location.reload()).then(()=>{n.value="";d.value="";});
                }
            }
        });
        
    </script>
</html>