import React from 'react'
import { Input, Menu } from 'antd'
import './AppLayout.scss'
import StoryList from './StoryList'
import SuggestionList from './SuggestionList'

function AppLayout({ children }) {
    return (
        <div className="app">
            <div className="header">
                <h1>Django-React</h1>
                <div className="search">
                    <Input.Search placeholder="검색어" />
                </div>
                <div className="topnav">
                    <Menu mode="horizontal">
                        <Menu.Item key="menu1">메뉴1</Menu.Item>
                        <Menu.Item key="menu2">메뉴2</Menu.Item>
                        <Menu.Item key="menu3">메뉴3</Menu.Item>
                    </Menu>
                </div>
            </div>
            <div className="sidebar">
                <StoryList style={{ marginBottom: '1rem' }} />
                <SuggestionList style={{ marginBottom: '1rem' }} />
            </div>
            <div className="contents">{children}</div>
            <div className="footer">&copy; 2021. test</div>
        </div>
    )
}

export default AppLayout
