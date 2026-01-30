import { useFetch } from "./Hooks";
export default function FetchingComponent(){
    const { data: users, loading } = useFetch(
    "https://jsonplaceholder.typicode.com/users"
  );
  if (loading) return <p>Loading...</p>;
  return (
    <ul>
      {users.map(user => (
        <li key={user.id}>{user.name}</li>
      ))}
    </ul>
  );
}