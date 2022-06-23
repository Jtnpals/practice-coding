import React from "react";
import "./SuggestionList.scss";
import { Card } from "antd";
import Suggestion from "./Suggestion";

const SuggestionList = ({style}) => {
    return (
        <div>
            <Card title="Suggestion for you" size="small">
                <Suggestion/>
            </Card>
        </div>
    )
};

export default SuggestionList;