def list_of_coins(amount, coins, memo):
	assert amount >= 0
	if amount == 0:
		return []
	else:
		if amount in memo:
			return memo[amount]
		else:
			options = [[coin] + list_of_coins(amount-coin,coins,memo) for coin in coins if coin<=amount]
			memo[amount] = min(options, key = len)
			return memo[amount]

def main(args):
	if len(args) < 2:
		print("Usage: coins <total <sequence of coin values>")
	else:
		amount = int(args[1])
		coins = [int(arg) for arg in args[2:]]

		print(amount, coins)

		memo = {}
		used = list_of_coins(amount, coins, memo)
		print(used)
		
import sys
if __name__ == "__main__":
	main(sys.argv)