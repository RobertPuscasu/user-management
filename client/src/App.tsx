import React from 'react'
import './App.css'
import { Observable } from 'rxjs'
import { Switch, withRouter, Redirect, RouteComponentProps } from 'react-router-dom'
import { IRootState } from '@interfaces/states'
import { useSelector, useDispatch, connect } from 'react-redux'
import { StylesProvider, ThemeProvider } from '@material-ui/core'
import RouteWithLayout from '@components/RouteWithLayout'
import { url } from '@src/helpers/constants'
import { Minimal as MinimalLayout } from '@src/layouts/index'
import Login from '@pages/Login'
import { theme } from '@theme/index'
import Loading from '@components/Loading'

export type TProps = RouteComponentProps

export const App: React.FC<TProps> = (props: TProps) => {
  //const isAuthenticated = useSelector<IRootState, Boolean>((state) => Boolean(state.user && state.user.email))

  let routes = (
    <Switch>
      <RouteWithLayout path={url.login} layout={MinimalLayout} component={Login} />
      <Redirect to={url.login} />
    </Switch>
  )

  return (
    <ThemeProvider theme={theme}>
      <StylesProvider injectFirst>
        <React.Suspense fallback={<Loading />}>{routes}</React.Suspense>
      </StylesProvider>
    </ThemeProvider>
  )
}

interface IMapStateToProps {
  isAuthenticated: boolean
  authChecked: boolean
}

interface IMapDispatchToProps {}

const mapStateToProps = (state: IRootState): IMapStateToProps => {
  return {
    isAuthenticated: Boolean(state.user && state.user.email),
    authChecked: state.user && state.user.authChecked,
  }
}

const mapDispatchToProps = (dispatch) => {}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App))
