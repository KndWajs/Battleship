import React from 'react';
import logo from './logo.svg';
import {Counter} from './features/counter/Counter';
import './App.scss';
import {Grid} from "./features/grid/Grid";
import {Title} from "./features/title/title";

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <Grid/>
                <Counter/>
                <Title/>
            </header>
        </div>
    );
}

export default App;
