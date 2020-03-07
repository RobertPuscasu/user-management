import axios, { AxiosError, AxiosRequestConfig } from 'axios'

export const requestInterceptor = () => {
  axios.interceptors.request.use((config: AxiosRequestConfig) => {
    config.baseURL = process.env.BACKEND_URL
    return config
  })
}

