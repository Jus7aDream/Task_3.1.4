$(async function () {
    await allUsers();
});
const table = $('#fill-users');

async function allUsers() {
    table.empty()
    fetch("http://localhost:8080/admin/users")
        .then(res => res.json())
        .then(data => {
            data.forEach(user => {
                let tableWithUsers = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>  
                            <td>${user.age}</td>                                               
                            <td>${user.username}</td>
                            <td>${user.roles.map(role => " " + role.name.substring(5))}</td>
                            <td>
                                <button type="button" class="btn btn-info" data-toggle="modal" id="buttonEdit"
                                data-action="edit" data-id="${user.id}" data-target="#editModal">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" data-toggle="modal" id="buttonDelete"
                                data-action="delete" data-id="${user.id}" data-target="#deleteModal">Delete</button>
                            </td>
                        </tr>)`;
                table.append(tableWithUsers);
            })
        })
}

$(async function () {
    await newUser();
});

async function newUser() {
    await fetch("http://localhost:8080/admin/authorities")
        .then(res => res.json())
        .then(roles => {
            roles.forEach(role => {
                let el = document.createElement("option");
                el.text = role.name.substring(5);
                el.value = role.authority;
                $('#newRoles')[0].appendChild(el);
            })
        })

    const form = document.forms.addNewUserTable;

    form.addEventListener('submit', addNewUser)

    function addNewUser(e) {
        e.preventDefault();
        let newUserRoles = [];
        for (let i = 0; i < form.roles.options.length; i++) {
            if (form.roles.options[i].selected)
                newUserRoles.push({
                    id: form.roles.options[i].value,
                    name: form.roles.options[i].value
                })
        }
        console.log(newUserRoles)
        fetch("http://localhost:8080/admin/users", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "firstName": form.firstName.value,
                "lastName": form.lastName.value,
                "age": form.age.value,
                "username": form.username.value,
                "password": form.password.value,
                "roles": newUserRoles
            })
        }).then(() => {
            form.reset();
            allUsers();
            $('#allUsersTable').click();
        })
    }

}

$('#deleteModal').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let id = button.data('id');
    showDeleteModal(id);
})

async function showDeleteModal(id) {
    let user = await getUser(id);
    let form = document.forms["deleteFormBody"];
    form.id.value = user.id;
    form.firstName.value = user.firstName;
    form.lastName.value = user.lastName;
    form.age.value = user.age;
    form.username.value = user.username;
    $('#rolesDelete').empty();
    await fetch("http://localhost:8080/admin/authorities")
        .then(res => res.json())
        .then(roles => {
            roles.forEach(role => {
                let selectedRole = false;
                for (let i = 0; i < user.roles.length; i++) {
                    if (user.roles[i].name === role.name) {
                        selectedRole = true;
                        break;
                    }
                }
                let el = document.createElement("option");
                el.text = role.name.substring(5);
                el.value = role.id;
                if (selectedRole) el.selected = true;
                $('#rolesDelete')[0].appendChild(el);
            })
        });
}

async function getUser(id) {
    let url = "http://localhost:8080/admin/users/" + id;
    let response = await fetch(url);
    return await response.json();
}

$(async function () {
    deleteUser();
});

function deleteUser() {
    const deleteForm = document.forms["deleteFormBody"];
    deleteForm.addEventListener("submit", ev => {
        ev.preventDefault();
        fetch("http://localhost:8080/admin/users/" + deleteForm.id.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(() => {
                $('#deleteModal').modal('hide')
                allUsers();
            })
    })
}

function getAllRoles() {
    return fetch("/admin/authorities")
        .then((response) => {
            return response.json();
        })
        .then((roles) => {
            console.log('all roles:')
            console.log(roles);
            return roles;
        })
}


$('#editModal').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let id = button.data('id');
    showEditModal(id);
})

async function showEditModal(id) {
    let user = await getUser(id);
    let form = document.forms["editFormBody"];
    form.id.value = user.id;
    form.firstName.value = user.firstName;
    form.lastName.value = user.lastName;
    form.age.value = user.age;
    form.username.value = user.username;
    form.password.value = user.password;

    $("#rolesEdit").empty();
    let selectEdit = document.getElementById('rolesEdit');
    let allRoles = await getAllRoles();

    allRoles.forEach((role) => {


        let option = document.createElement('option');
        option.setAttribute('value', role.name);
        option.setAttribute('id', role.id);
        option.setAttribute('name', role.name);
        option.appendChild(document.createTextNode(role.name.substring(5)));
        selectEdit.appendChild(option);
        let selectedRole = false;
        for (let i = 0; i < user.roles.length; i++) {
            if (user.roles[i].name === role.name) {
                selectedRole = true;
                break;
            }
        }
        if (selectedRole) option.selected = true
    })


}

$(async function () {
    editUser();
});

function editUser() {
    const editForm = document.forms["editFormBody"];
    editForm.addEventListener("submit", ev => {
        ev.preventDefault();
        let editUserRoles = [];
        for (let i = 0; i < editForm.roles.options.length; i++) {
            if (editForm.roles.options[i].selected) editUserRoles.push({
                id: editForm.roles.options[i].value,
                name: "ROLE_" + editForm.roles.options[i].text
            })
        }

        fetch("http://localhost:8080/admin/users/" + editForm.id.value, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: editForm.id.value,
                name: editForm.firstName.value,
                surname: editForm.lastName.value,
                age: editForm.age.value,
                username: editForm.username.value,
                password: editForm.password.value,
                roles: editUserRoles
            })
        }).then(() => {
            $('#editModal').modal('hide')
            allUsers()

        })
    })
}