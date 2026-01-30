import ProfileCard from './ProfileCard.jsx'

export default function Main() {
  const students = [
    {
      name:"Sanjeevi",
      age:17,
      email:"uname@domain.tld",
      phone:"12341234",
      url:"https://picsum.photos/300/300"
    },
    {
      name:"John Doe",
      age:"17",
      email:"uname2@domain.tld",
      phone:"12341234234",
      url:"https://picsum.photos/300/300"
    }
  ]

  return (
  
  students.map((d,idx)=>
    <ProfileCard key={idx} name={d.name} age={d.age} email={d.email} phone={d.phone} url={d.url}></ProfileCard>
  )
)
}
