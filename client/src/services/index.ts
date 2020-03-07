import logger from './loggerService'
import { UserService } from './UserService'
import { AuthService } from './AuthService';

export default {
  logger,
  api: {
    users: new UserService(),
    auth: new AuthService()
  }
}
