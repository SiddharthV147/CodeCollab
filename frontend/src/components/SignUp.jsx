import { React, use, useState } from 'react'

function Login() {

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const onSubmit = () => {
        console.log(name)
        console.log(email)
        console.log(password)
    }

    return (
        <>
            <div className='justify-center items-center border-2 p-4 rounded-3xl'>
                <h1 className='text-black text-3xl flex justify-center items-center font-bold'>Sign Up</h1>
                <div>
                    <div className='p-4 gap-4'>
                        <h1>Name</h1>
                        <input 
                            className='p-2 border-black border-2 rounded-2xl'
                            type='name'
                            id="name"
                            placeholder='Name'
                            value={email}
                            onChange={(e) => setName(e.target.value)}
                            required
                        />
                    </div>
                    <div className='p-4 gap-4'>
                        <h1>Email</h1>
                        <input 
                            className='p-2 border-black border-2 rounded-2xl'
                            type='email'
                            id="email"
                            placeholder='example@gmail.com'
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
                    <div className='p-4 gap-4'>
                        <h1>Password</h1>
                        <input 
                            className='p-2 border-black border-2 rounded-2xl'
                            type="password" 
                            id="password"
                            placeholder='Password'
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <div className='mt-7 text-sm'>
                        Dont have an accout ? <a className='text-blue-800' href="#">Signup</a>
                    </div>
                    <div
                        className='flex justify-center items-center hover:pointer bg-blue-400 rounded-2xl cursor-pointer p-2'
                        onClick={onSubmit}>
                        Login
                    </div>
                </div>
            </div>
        </>
    )
}

export default Login