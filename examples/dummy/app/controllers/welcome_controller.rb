class WelcomeController < ApplicationController

  def index
    @users = User.count
    render plain: "Hello, world! I have #{@users} users."
  end

end
