import logger from './loggerService'
import { UserService } from './UserService'

export default {
  logger,
  api: {
    users: new UserService()
  }
}
