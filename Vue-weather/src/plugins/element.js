import Vue from 'vue'
import {
    Button, Form, FormItem, Input, Avatar, Message, Container, Header, Aside, Main,
    Menu, Submenu, MenuItem, Breadcrumb, BreadcrumbItem, Card, Row, Col, Table, TableColumn,
    Switch, Tooltip, Pagination, Dialog, MessageBox, Tag, Tree, Select, Option, ButtonGroup,
    Footer
} from 'element-ui'

Vue.use(Button)
Vue.use(Form)
Vue.use(Input)
Vue.use(Avatar)
Vue.use(Container)
Vue.use(Header)
Vue.use(Aside)
Vue.use(Main)
Vue.use(FormItem)
Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItem)
Vue.use(Breadcrumb)
Vue.use(BreadcrumbItem)
Vue.use(Card)
Vue.use(Row)
Vue.use(Col)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Switch)
Vue.use(Tooltip)
Vue.use(Pagination)
Vue.use(Dialog)
Vue.use(Tag)
Vue.use(Tree)
Vue.use(Select)
Vue.use(Option)
Vue.use(ButtonGroup)
Vue.use(Footer)
Vue.prototype.$confirm = MessageBox.confirm
Vue.prototype.$message = Message
