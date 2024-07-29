export function SignUp() {

    const [username, setUsername] = useState()

    const onChangeUsername = (event) => {
        setUsername(event.target.value);
    }
    console.log(username)
    return (
        <>
            <h1>Sign Up</h1>
            <div>
                <label htmlFor="username">Username</label>
                <input id="username" type="text" placeholder="Username" onChange={onChangeUsername}/>
            </div>
            <div>
                <label htmlFor="email">E-mail</label>
                <input id="email" type="email" placeholder="E-mail"/>
            </div>
            <div>
                <label htmlFor="password">Password</label>
                <input id="password" type="password" placeholder="Password"/>
            </div>
            <div>
                <label htmlFor="passwordRepeat">Password Repeat</label>
                <input id="passwordRepeat" type="password" placeholder="Password"/>
            </div>
            <button type="submit">Sign Up</button>
        </>)
}