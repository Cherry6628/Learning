// import { useState } from 'react'
import Welcome from "./components/Welcome";
import { Header } from './components/Header';
import { Greeting } from "./components/Greeting";
import { RenderList } from "./components/RenderingLists";
import { ProductCard } from "./components/ProductCard";
function App() {
  // const [count, setCount] = useState(0)

  return (
    <>
    <Header></Header>
    <Welcome></Welcome>
    <Greeting></Greeting>
    <RenderList></RenderList>
    <ProductCard name="Television"price="$1234"stock="1"></ProductCard>
    </>
  )
}

export default App
