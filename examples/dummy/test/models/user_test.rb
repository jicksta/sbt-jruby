require 'test_helper'

class UserTest < ActiveSupport::TestCase
  test "can create and find users" do
    expected_count = User.count + 1
    User.create name: "Anakin", email: "anakin@tatooine.planet"
    assert_equal expected_count, User.count
  end
end
