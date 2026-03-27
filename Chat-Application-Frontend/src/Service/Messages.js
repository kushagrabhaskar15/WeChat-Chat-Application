const SEND_MESSAGE = import.meta.env.VITE_SEND_MESSAGE;

async function sendMessage(message){
    try{
        const res = await fetch(SEND_MESSAGE,{
            method:"POST",
            headers:{
                "Content-Type": "application/json"
            },
            body:JSON.stringify({
                content:message
            })
        });
        if(!res.ok) throw new Error("Some error arrived!");
    }
    catch(err){
        console.log("In the catch block :",err);
        console.throw(err);    
    }
}