import Welcome from "./Welcome";
import Header from "./Header";
import Greeting from "./Greeting";
import RenderList from "./RenderingLists";
import ProductCard from "./ProductCard";

export default function Main() {
    return (
        <>
            <Header></Header>
            <Welcome></Welcome>
            <Greeting></Greeting>
            <RenderList></RenderList>
            <ProductCard
                name="Television"
                price="$1234"
                stock="1"
            ></ProductCard>
        </>
    );
}
